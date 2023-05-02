package io.github.emanuelvictor.commons.converter;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Converter<D, O> {

    /**
     * @return {@link String} id from Converter to comparisson.
     */
    public String getId() {
        return this.getClass().getCanonicalName().toUpperCase();
    }

    public abstract D convertAll(final O origin);

    public List<D> convertAll(final List<O> origins) {
        if (origins == null) return null;
        return origins.stream().map(this::convertAll).collect(Collectors.toList());
    }

    public Set<D> convertAll(final Set<O> origins) {
        if (origins == null) return null;
        return origins.stream().map(this::convertAll).collect(Collectors.toSet());
    }

    public abstract D convertWithoutRecursive(final O origin);

    public List<D> convertWithoutRecursive(final List<O> origins) {
        if (origins == null) return null;
        return origins.stream().map(this::convertWithoutRecursive).collect(Collectors.toList());
    }

    public Set<D> convertWithoutRecursive(final Set<O> origins) {
        if (origins == null) return null;
        return origins.stream().map(this::convertWithoutRecursive).collect(Collectors.toSet());
    }

    public abstract D convertRecursive(final O origin);

    public List<D> convertRecursive(List<O> buys) {
        return buys.stream().map(this::convertRecursive).collect(Collectors.toList());
    }
    public Set<D> convertRecursive(Set<O> buys) {
        return buys.stream().map(this::convertRecursive).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            return true;
        else
            return getId().equals(((Converter<?, ?>) obj).getId());
    }

}
