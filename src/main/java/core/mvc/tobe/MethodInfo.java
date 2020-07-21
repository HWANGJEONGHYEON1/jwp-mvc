package core.mvc.tobe;

import core.mvc.tobe.helper.HandlerMethodHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By kjs4395 on 2020-06-25
 */
public class MethodInfo {
    private List<ParameterInfo> parameters = new ArrayList<>();

    public MethodInfo(Method method) {
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            parameters.add(new ParameterInfo(method, i));
        }
    }

    public Object[] bindingParameters(HttpServletRequest request, List<HandlerMethodHelper> methodHelpers) {
        return this.parameters
                .stream()
                .map(parameter -> parameter.invokeParameterBind(request,methodHelpers))
                .toArray();
    }
}