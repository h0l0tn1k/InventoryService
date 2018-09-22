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
}
*/