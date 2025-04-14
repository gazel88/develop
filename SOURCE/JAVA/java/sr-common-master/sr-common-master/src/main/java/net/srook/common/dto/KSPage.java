package net.srook.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import lombok.ToString;

@ToString(of = {"totalElements", "size", "page", "totalPage"})
public class KSPage<T> implements Serializable {
    private long totalElements;
    private int size;
    private int page;
    private int totalPage;
    private List<T> content = new ArrayList<>();

    protected KSPage() {
    }

    private KSPage(final long totalElements, final int size, final int page, final int totalPage, final List<T> content) {
        this.totalElements = totalElements;
        this.size = size;
        this.page = page;
        this.totalPage = totalPage;
        this.content = content;
    }

    private KSPage(final Page<T> page) {
        this.totalElements = page.getTotalElements();
        this.size = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
        content.addAll(page.getContent());
    }

    public static <T> KSPage<T> of(final Page<T> page) {
        return new KSPage<>(page);
    }

    public static <T> KSPage<T> of(final long totalElements, final int size, final int page, final List<T> content) {
        final int totalPage = (int) (totalElements / size + (totalElements % size > 0 ? 1 : 0));
        return new KSPage<>(totalElements, size, page, totalPage, content);
    }

    public long getTotalElements() {
        return totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }
    public int getTotalPage() { return totalPage; }

    public <U> KSPage<U> map(Function<? super T, ? extends U> converter) {
        return KSPage.of(this.totalElements, this.size, this.page, this.content.stream().map(converter).collect(Collectors.toList()));
    }

    public KSPage<T> convertContent(final ObjectMapper mapper, final Class<T> object) {
        CollectionType listType = mapper.getTypeFactory()
                .constructCollectionType(List.class, object);
        return KSPage.of(this.totalElements, this.size, this.page, mapper.convertValue(this.content, listType));
    }
}
