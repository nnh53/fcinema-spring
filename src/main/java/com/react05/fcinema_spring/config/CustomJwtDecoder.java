package com.react05.fcinema_spring.config;

//@Component
// implements JwtDecoder
public class CustomJwtDecoder {
//    @Value("${jwt.signerKey}")
//    private String signerKey;
//
//    @Autowired
//    private AuthenticationServiceImpl authenticationServiceImpl;
//    private NimbusJwtDecoder nimbusJwtDecoder = null;
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        try{
//
//            var response =  authenticationServiceImpl.introspect(IntrospectRequest.builder()
//                    .token(token)
//                    .build());
//
//            if(!response.isValid()){
//                throw new JwtException("Token invalid");
//            }
//        }catch (JOSEException | ParseException e){
//            throw new JwtException(e.getMessage());
//        }
//
//        if(Objects.isNull(nimbusJwtDecoder)){
//            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(),"HS512");
//            nimbusJwtDecoder = NimbusJwtDecoder
//                    .withSecretKey(secretKeySpec)
//                    .macAlgorithm(MacAlgorithm.HS512)
//                    .build();
//        }
//
//        return nimbusJwtDecoder.decode(token);
//    }
}
