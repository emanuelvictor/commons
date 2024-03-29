package io.github.emanuelvictor.commons.persistence.generic;


import io.github.emanuelvictor.commons.persistence.IPersistentEntity;

/*
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
public abstract class PersistentEntity implements IPersistentEntity<Integer> {

    /*
     *
     */
    protected Integer id;

    /*
     *
     */
    public PersistentEntity() {
    }

    /*
     * @param id Integer
     */
    public PersistentEntity(final Integer id) {
        this.id = id;
    }

    /*
     * {@inheritDoc }
     *
     * @return
     */
    @Override
    public boolean isSaved() {
        return this.id != null && this.id != 0;
    }

    /*
     * @return Integer id of the persistent entity
     */
    public Integer getId() {
        return id;
    }

    /*
     * @param id Integer of the persistent entity
     */
    public void setId(final Integer id) {
        this.id = id;
    }
}
