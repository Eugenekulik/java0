CREATE USER bp_app@localhost
identified by 'bppassword';

GRANT SELECT,INSERT,UPDATE,DELETE, CREATE temporary tables
ON beauty_parlor.*
TO bp_app@localhost;

GRANT SELECT,INSERT,UPDATE,DELETE, CREATE temporary tables
    ON test.*
    TO bp_app@localhost;