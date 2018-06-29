package site.yuyanjia.template.website.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.ObjectUtils;
import site.yuyanjia.template.common.contant.ResponseEnum;
import site.yuyanjia.template.common.contant.ResultEnum;
import site.yuyanjia.template.common.util.FastJsonUtil;
import site.yuyanjia.template.website.dto.AjaxBaseResponseDTO;

import java.util.Collection;

/**
 * ajax工具
 *
 * @author seer
 * @date 2018/6/12 15:20
 */
public class AjaxUtil {

    /**
     * 处理成功
     *
     * @return
     */
    public static String resultSuccess() {
        return baseResponse(ResponseEnum.SUCCESS, ResultEnum.处理成功, null, 0);
    }

    /**
     * 处理成功
     *
     * @param data
     * @return
     */
    public static String resultSuccess(Collection data, int count) {
        return baseResponse(ResponseEnum.SUCCESS, ResultEnum.处理成功, data, count);
    }

    /**
     * 处理失败
     *
     * @param ajaxResultEnum
     * @return
     */
    public static String resultFailed(ResultEnum ajaxResultEnum) {
        return baseResponse(ResponseEnum.SUCCESS, ajaxResultEnum, null, 0);
    }

    /**
     * 处理失败
     *
     * @param ajaxResultEnum
     * @param data
     * @return
     */
    public static String resultFailed(ResultEnum ajaxResultEnum, Collection<Object> data, int count) {
        return baseResponse(ResponseEnum.SUCCESS, ajaxResultEnum, data, count);
    }

    /**
     * 响应失败
     *
     * @return
     */
    public static String responseFailed() {
        return baseResponse(ResponseEnum.FAILED, null, null, 0);
    }


    /**
     * 响应
     *
     * @param responseEnum
     * @param ajaxResultEnum
     * @param data
     * @return
     */
    private static String baseResponse(ResponseEnum responseEnum, ResultEnum ajaxResultEnum, Collection<Object> data, int count) {
        AjaxBaseResponseDTO ajaxBaseResponseDTO = new AjaxBaseResponseDTO();
        ajaxBaseResponseDTO.setResponseCode(responseEnum.alias);
        ajaxBaseResponseDTO.setResponseMsg(responseEnum.toString());

        if (!ObjectUtils.isEmpty(ajaxResultEnum)) {
            ajaxBaseResponseDTO.setResultCode(ajaxResultEnum.alias);
            ajaxBaseResponseDTO.setResultMsg(ajaxResultEnum.toString());
        }

        if (!ObjectUtils.isEmpty(data)) {
            ajaxBaseResponseDTO.setData(data);
        }
        ajaxBaseResponseDTO.setAmount(count);

        SerializeConfig serializeConfig = FastJsonUtil.getSerializeConfig();
        return JSON.toJSONString(ajaxBaseResponseDTO, serializeConfig, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 扩展响应
     *
     * @param responseEnum
     * @param ajaxResultEnum
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends AjaxBaseResponseDTO> String extendResponse(ResponseEnum responseEnum, ResultEnum ajaxResultEnum, T t) {
        t.setResponseCode(responseEnum.alias);
        t.setResponseMsg(responseEnum.toString());

        if (!ObjectUtils.isEmpty(ajaxResultEnum)) {
            t.setResultCode(ajaxResultEnum.alias);
            t.setResultMsg(ajaxResultEnum.toString());
        }

        SerializeConfig serializeConfig = FastJsonUtil.getSerializeConfig();
        return JSON.toJSONString(t, serializeConfig, SerializerFeature.WriteMapNullValue);
    }
}

