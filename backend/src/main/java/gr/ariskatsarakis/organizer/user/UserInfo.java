package gr.ariskatsarakis.organizer.user;

import java.util.List;

import gr.ariskatsarakis.organizer.wallet.Wallet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private int id;
        private String name;
        private String email;
        private String password;
        private String roles;

        @OneToMany
        @JoinColumn(name = "user_id")
        private List<Wallet> wallets;
}
