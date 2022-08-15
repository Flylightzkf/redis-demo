package com.liang.redisdemo.utils.redis;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * 缓存数据时间格式不正确问题
 *
 * @author Mr.Liang
 * @date 2022/5/23 11:06
 */

public class JodaDateTimeJsonSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toString("yyyy-MM-dd HH:mm:ss"));

    }
}
