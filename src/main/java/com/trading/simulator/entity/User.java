package com.trading.simulator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password; // store encoded password

    private String roles; // e.g. ROLE_USER

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    // ----- Constructors -----
    public User() {}

    public User(Long id, String username, String email, String password, String roles, Wallet wallet) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.wallet = wallet;
    }

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }

    public Wallet getWallet() { return wallet; }
    public void setWallet(Wallet wallet) { this.wallet = wallet; }

    // ----- Manual Builder -----
    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private String roles;
        private Wallet wallet;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roles(String roles) {
            this.roles = roles;
            return this;
        }

        public Builder wallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }

        public User build() {
            return new User(id, username, email, password, roles, wallet);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
