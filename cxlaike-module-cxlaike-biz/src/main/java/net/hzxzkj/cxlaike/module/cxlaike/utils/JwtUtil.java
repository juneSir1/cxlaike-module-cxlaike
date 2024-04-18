package net.hzxzkj.cxlaike.module.cxlaike.utils;

import io.jsonwebtoken.*;
import net.hzxzkj.cxlaike.framework.common.util.date.DateUtils;
import net.hzxzkj.cxlaike.framework.common.util.json.JsonUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jianan.han
 * @date 2023-05-31 16:35
 * @description
 */
public class JwtUtil {

    private static String signature = "app:userId";
    private static String matrix_task = "matrix:task";

    /**
     * 用户id加密
     * @author hjn
     * @date 2023-05-31 16:42
     * @param userId 用户id
     * @return java.lang.String
     */
    public static String addState(Long userId, String openId, Integer userType, Date validityDate, List<Long> groupIds
            ,String poiId,String poiName,Integer provinceCode,String provinceName,Integer cityCode,String cityName
            ,Integer countyCode,String countyName,String address, String location){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("userId", userId)
                .claim("openId",openId)
                .claim("userType",userType)
                .claim("validityDate",validityDate)
                .claim("groupIds",groupIds)
                .claim("poiId",poiId)
                .claim("poiName",poiName)
                .claim("provinceCode",provinceCode)
                .claim("provinceName",provinceName)
                .claim("cityCode",cityCode)
                .claim("cityName",cityName)
                .claim("countyCode",countyCode)
                .claim("countyName",countyName)
                .claim("address",address)
                .claim("location",location)
                .setSubject("app-test")
                //.setExpiration(new Date(System.currentTimeMillis() + time)) //有效时间1天
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static Claims parse(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
//        System.out.println(claims.get("userId"));
//        System.out.println(claims.get("commodityId"));
//        System.out.println(claims.getId());
//        System.out.println(claims.getSubject());
//        System.out.println(claims.getExpiration());
        return claims;
    }


//    public static void main(String[] args) {
//        String s = addState(121212L, "", 1, DateUtils.addTime(Duration.ofDays(-1)), null, "212312");
//
//
//        Claims parse = parse(s);
//        Long userId = parse.get("userId",Long.class);
//        String openId = parse.get("openId",String.class);
//        Integer userType = parse.get("userType",Integer.class);
//        Date validityDate = parse.get("validityDate", Date.class);
//        String poiId = parse.get("poiId",String.class);
//        List<Long> groupIds = parse.get("groupIds",List.class);
//
//        if(validityDate != null && new Date().after(validityDate)){
//            System.out.println("过期");
//        }
//
//
//
//        if(validityDate != null){
//            System.out.println("有有效期");
//        }else {
//            System.out.println("没有有效期");
//        }
//
//
//        System.out.println(userId);
//        System.out.println(openId);
//        System.out.println(userType);
//        System.out.println(validityDate);
//        System.out.println(poiId);
//        System.out.println(JsonUtils.toJsonString(groupIds));
//
//    }

    public static String addStateForScan(Long taskId){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("taskId", taskId)
                .setSubject("merchant-matrix-task")
                //.setExpiration(new Date(System.currentTimeMillis() + time)) //有效时间1天
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, matrix_task)
                .compact();
        return jwtToken;
    }

    public static Claims parseForScan(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(matrix_task).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
//        System.out.println(claims.get("userId"));
//        System.out.println(claims.get("commodityId"));
//        System.out.println(claims.getId());
//        System.out.println(claims.getSubject());
//        System.out.println(claims.getExpiration());
        return claims;
    }
}
