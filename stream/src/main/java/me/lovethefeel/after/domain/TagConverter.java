package me.lovethefeel.after.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Converter
public class TagConverter implements AttributeConverter<Set<String>, String> {
    public static final String EMPTY_STRING = "";
    public static final char SEPARATOR = ',';

    @Override
    public String convertToDatabaseColumn(final Set<String> hobbyList) {
        if (CollectionUtils.isEmpty(hobbyList)) {
            return EMPTY_STRING;
        }
        return StringUtils.join(hobbyList, SEPARATOR);
    }

    @Override
    public Set<String> convertToEntityAttribute(final String hobby) {
        final Set<String> ret = new HashSet<>();

        if (Objects.isNull(hobby)) {
            return ret;
        }

        final String[] afterSplit = StringUtils.split(hobby, SEPARATOR);
        for (final String str : afterSplit) {
            ret.add(str.trim());
        }
        return ret;
    }
}
