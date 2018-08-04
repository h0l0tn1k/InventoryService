package cz.siemens.inventory.controllers;

/*
@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UserController {

    private UserDao userDao;
    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<User> findAll(){
        logger.info("findAll()");
        return userDao.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final User findById(@PathVariable("id") Long id) throws Exception {
        logger.info("findById({id})", id);
        try {
            return userDao.findById(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody User user) throws Exception {
        logger.info("create({user})", user.toString());
        try {
            userDao.save(user);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id})", id);
        try {
            userDao.delete(userDao.findById(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
*/