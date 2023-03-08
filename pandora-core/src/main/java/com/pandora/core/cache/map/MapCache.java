package com.pandora.core.cache.map;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.pandora.dao.system.enums.dictionary.BusinessTypeEnum;
import com.pandora.dao.system.enums.dictionary.YesOrNoEnum;
import com.pandora.dao.system.enums.dictionary.StatusEnum;
import com.pandora.dao.system.enums.dictionary.GenderEnum;
import com.pandora.dao.system.enums.dictionary.NoticeStatusEnum;
import com.pandora.dao.system.enums.dictionary.NoticeTypeEnum;
import com.pandora.dao.system.enums.dictionary.OperationStatusEnum;
import com.pandora.dao.system.enums.dictionary.VisibleStatusEnum;
import com.pandora.dao.system.enums.interfaces.DictionaryEnum;
import com.pandora.dao.system.result.DictionaryData;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 本地一级缓存  使用Map
 *
 * @author valarchie
 */
public class MapCache {

    private final static Map<String, List<DictionaryData>> DICTIONARY_CACHE = MapUtil.newHashMap(128);

    static {
        initDictionaryCache();
    }

    private static void initDictionaryCache() {

        DICTIONARY_CACHE.put(BusinessTypeEnum.getDictName(), arrayToList(BusinessTypeEnum.values()));
        DICTIONARY_CACHE.put(YesOrNoEnum.getDictName(), arrayToList(YesOrNoEnum.values()));
        DICTIONARY_CACHE.put(StatusEnum.getDictName(), arrayToList(StatusEnum.values()));
        DICTIONARY_CACHE.put(GenderEnum.getDictName(), arrayToList(GenderEnum.values()));
        DICTIONARY_CACHE.put(NoticeStatusEnum.getDictName(), arrayToList(NoticeStatusEnum.values()));
        DICTIONARY_CACHE.put(NoticeTypeEnum.getDictName(), arrayToList(NoticeTypeEnum.values()));
        DICTIONARY_CACHE.put(OperationStatusEnum.getDictName(), arrayToList(OperationStatusEnum.values()));
        DICTIONARY_CACHE.put(VisibleStatusEnum.getDictName(), arrayToList(VisibleStatusEnum.values()));

    }

    public static Map<String, List<DictionaryData>> dictionaryCache() {
        return DICTIONARY_CACHE;
    }

    @SuppressWarnings("rawtypes")
    private static List<DictionaryData> arrayToList(DictionaryEnum[] dictionaryEnums) {
        if(ArrayUtil.isEmpty(dictionaryEnums)) {
            return ListUtil.empty();
        }
        return Arrays.stream(dictionaryEnums).map(DictionaryData::new).collect(Collectors.toList());
    }


}
