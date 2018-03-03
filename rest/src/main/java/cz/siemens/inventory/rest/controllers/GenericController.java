package cz.siemens.inventory.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class GenericController<E extends Object, ID extends Serializable> {

    private Logger logger = LoggerFactory.getLogger(GenericController.class);
    private CrudRepository<E, ID> repository;

    protected GenericController(CrudRepository<E, ID> repo) {
        this.repository = repo;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public @ResponseBody E get(@PathVariable ID id) {
        return this.repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody final List<E> listAll() {
        Iterable<E> all = this.repository.findAll();
        return Lists.newArrayList(all);
    }

    @RequestMapping(method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public void create(@RequestBody E entity) {
        logger.debug("create() with body {} of type {}", entity, entity.getClass());

        this.repository.save(entity);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable ID id) {
        this.repository.delete(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
    public void update(@PathVariable ID id, @RequestBody E entity) {
        E entityFromDb = this.repository.findOne(id);
        try {
            BeanUtils.copyProperties(entityFromDb, entity);
        }
        catch (Exception e) {
            logger.warn("while copying properties", e);
            //throw Throwables.propagate(e);
            throw e;
        }

        logger.debug("merged entity: {}", entityFromDb);

        this.repository.save(entityFromDb);
    }

}
