package com.juanprato.mytouch.common.resolvers;

import com.juanprato.mytouch.common.annotations.RequestToken;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class RequestTokenResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (supportsParameter(methodParameter)) {
            if (nativeWebRequest.getHeader("Authorization") != null) {
                return Objects.requireNonNull(nativeWebRequest.getHeader("Authorization")).substring(7);
            }
        }
        return null;
    }
}
