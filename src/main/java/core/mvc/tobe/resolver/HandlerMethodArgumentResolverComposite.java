package core.mvc.tobe.resolver;

import core.mvc.tobe.resolver.method.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HandlerMethodArgumentResolverComposite implements HandlerMethodArgumentResolver {

    private static final HandlerMethodArgumentResolvers DEFAULT_RESOLVERS = HandlerMethodArgumentResolvers.of(
            List.of(
                new BasicArgumentResolver(),
                new PathArgumentResolver(),
                new ModelAttributeMethodProcessor(new BasicArgumentResolver())
            )
    );


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return DEFAULT_RESOLVERS.getHandlerMethodArgumentResolvers()
                .stream()
                .anyMatch(resolver -> resolver.supportsParameter(parameter));
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, HttpServletRequest request, HttpServletResponse response) {
        return DEFAULT_RESOLVERS.getHandlerMethodArgumentResolvers()
                .stream()
                .filter(resolver -> resolver.supportsParameter(methodParameter))
                .findAny()
                .map(resolver -> resolver.resolveArgument(methodParameter, request, response))
                .orElseThrow(() -> new IllegalArgumentException("적합한 리졸버가 없습니다."));
    }
}
