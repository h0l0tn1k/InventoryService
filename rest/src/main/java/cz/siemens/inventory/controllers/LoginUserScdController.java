package cz.siemens.inventory.controllers;

/*
@RestController
@RequestMapping(ApiUris.ROOT_URI_LOGIN_USER_SCD)
public class LoginUserScdController {

    private GenericDao<LoginUserScdDao> userDao;
    final static Logger logger = LoggerFactory.getLogger(LoginUserScdController.class);

    @Autowired
    public LoginUserScdController(GenericDao<UserScd> userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserScd findById(@PathVariable("id") Long id) throws Exception {
        logger.info("findById({id}) called", id);
        try {
            return userDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody UserScd user) throws Exception {
        logger.info("create({user}) called", user.toString());
        try {
            userDao.create(user);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id}) called", id);
        try {
            userDao.delete(userDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
*/