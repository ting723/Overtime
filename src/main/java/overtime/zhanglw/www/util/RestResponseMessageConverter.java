package overtime.zhanglw.www.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JSON转换器.在webmvc的controller中,如果使用{@link ResponseBody}注解一个方法,并且返回值是
 * {@link RestResponse}.那么结果可以被自动序列化为json
 * 输出格式可以是json,也可以是jsonp,这取决于请求路径中是.json还是.jsonp
 * jsonp的参数名默认取决于callback参数,参数名可以配置
 * 
 * @author lijia
 *
 */
public class RestResponseMessageConverter extends
		AbstractHttpMessageConverter<Map<String, Object>> {
	private final static Charset UTF8 = Charset.forName("UTF-8");
	private final static String JSONP_FUNC_NAME = "callback";
	private Charset charset = UTF8;
	private String jsonpFuncName = JSONP_FUNC_NAME;
	private SerializeConfig config = SerializeConfig.getGlobalInstance();
	private SerializeFilter[] filters = new SerializeFilter[0];
	private SerializerFeature[] features = new SerializerFeature[0];

	public RestResponseMessageConverter() {
		super(new MediaType("application", "json", UTF8), new MediaType(
				"application", "*+json", UTF8));
	}

	public void setJsonpFuncName(String jsonpFuncName) {
		this.jsonpFuncName = jsonpFuncName;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setConfig(SerializeConfig config) {
		this.config = config;
	}

	public void setFilters(SerializeFilter[] filters) {
		this.filters = filters;
	}

	public void setFeatures(SerializerFeature[] features) {
		this.features = features;
	}

	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	private boolean requestJsonp(HttpServletRequest request) {
		return request.getRequestURI().endsWith(".jsonp");
	}

	private String getJsonpFunc(HttpServletRequest request) {
		String func = request.getParameter(jsonpFuncName);
		return StringUtils.isEmpty(func) ? "null" : func;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Map.class.isAssignableFrom(clazz);
	}

	@Override
	protected Map<String, Object> readInternal(
			Class<? extends Map<String, Object>> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		// 这个方法调用不到,因为这个类只会用于json输出
		return null;
	}

	@Override
	protected void writeInternal(Map<String, Object> t,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		OutputStream out = outputMessage.getBody();
		HttpServletRequest request = getRequest();
		StringBuilder buffer = new StringBuilder();
		boolean requestJsonp = requestJsonp(request);
		if (requestJsonp) {
			buffer.append(getJsonpFunc(request)).append('(');
		}
		buffer.append(JSON.toJSONString(t, config, filters, features));
		if (requestJsonp) {
			buffer.append(");");
		}
		byte[] bytes = buffer.toString().getBytes(charset);
		out.write(bytes);
		out.flush();
	}
}
