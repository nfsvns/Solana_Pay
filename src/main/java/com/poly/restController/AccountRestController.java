    package com.poly.restController;

    import com.poly.entity.Account;
    import com.poly.service.AccountService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @CrossOrigin("*")
    @RestController
    @RequestMapping("/rest/accounts")
    public class AccountRestController {
        @Autowired
        AccountService accountService;

        @Autowired

        @GetMapping
        public List<Account> getAll() {
            return accountService.findAll();
        }

        @GetMapping("/{id}")
        public Account getOne(@PathVariable("id") String username) {
            return accountService.findById(username);
        }

        @PostMapping
        public Account post(@RequestBody Account account) {
            accountService.create(account);
            return account;
        }

        @PutMapping("/{id}")
		public Account put(@PathVariable("id") String username, @RequestBody Account account) {
			return accountService.update(account);
		}

        @DeleteMapping("/{id}")
        public void delete(@PathVariable("id") String username) {
        	System.out.println(username);
            accountService.delete(username);

        }

    }
