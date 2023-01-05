/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.utils;

import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ASUS
 */
public class GetAuthContext {
    public static void setAuthorization(Authentication auth){
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    
    public static Authentication getAuthorization(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static Set<String> getAuthorityDetail(Authentication auth){
        return AuthorityUtils.authorityListToSet(auth.getAuthorities());
    }
}
