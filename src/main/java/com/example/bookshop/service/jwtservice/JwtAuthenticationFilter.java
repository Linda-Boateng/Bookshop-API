package com.example.bookshop.service.jwtservice;

import static com.example.bookshop.util.ConstantStrings.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.bookshop.dto.request.UserDto;
import com.example.bookshop.dto.response.JwtErrorResponseDto;
import com.example.bookshop.service.userservice.UserDetailsServiceImpl;
import com.example.bookshop.service.userservice.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserServiceImpl userService;
  private final UserDetailsServiceImpl userDetailsService;

  @Value("${jwt.secret}")
  private String secret;

  /**
   * This method is used to filter the request and response
   *
   * @param request the request object
   * @param response the response object
   * @param filterChain the filter chain object
   * @throws ServletException if there is an error in the servlet
   * @throws IOException if there is an error in the input/output
   */
  @Override
  public void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = extractJwtFromRequest(request);
      if (!jwt.isEmpty()) {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        Algorithm algorithm = Algorithm.HMAC256(keyBytes);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String email = decodedJWT.getClaim(EMAIL).asString();

        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setFirstName(decodedJWT.getClaim(FIRSTNAME).asString());
        userDto.setLastName(decodedJWT.getClaim(LASTNAME).asString());
        userDto.setRole(decodedJWT.getClaim(ROLE).asString());

        request.setAttribute(USERDTO, userDto);

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(email);
          if (userDetails == null) {
            userService.registerUser(userDto);
            userDetails = userDetailsService.loadUserByUsername(email);
          }
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (BadCredentialsException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      new ObjectMapper()
          .writeValue(response.getOutputStream(), new JwtErrorResponseDto(MESSAGE, e.getMessage()));
      return;
    } catch (JWTVerificationException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      new ObjectMapper()
          .writeValue(response.getOutputStream(), new JwtErrorResponseDto(e.getMessage(), MESSAGE));
      return;
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getServletPath();
    return path.contains("/api/public/v1")
        || path.contains("/swagger-ui")
        || path.contains("/v3/api-docs");
  }

  /**
   * This method is used to extract the jwt from the request
   *
   * @param request the request object
   * @return the jwt token
   */
  public String extractJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
      return bearerToken.substring(TOKEN_PREFIX.length());
    }
    throw new BadCredentialsException(INVALID_TOKEN);
  }
}
