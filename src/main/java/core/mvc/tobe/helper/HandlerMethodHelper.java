package core.mvc.tobe.helper;

import core.mvc.tobe.ParameterInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By kjs4395 on 2020-06-24
 * <p>
 * 메소드 invoke 를 하기 위해 request에서 적절한 값을 찾아내는 과정
 */
public interface HandlerMethodHelper {

    boolean support(ParameterInfo parameterInfo);

    Object bindingProcess(ParameterInfo parameterInfo, HttpServletRequest request);
}