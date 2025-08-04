package gr.ariskatsarakis.organizer.user;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
        private UserInfoService service;

}
