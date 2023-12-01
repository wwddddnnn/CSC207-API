package CACoding.src.entity;

import java.time.LocalDateTime;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();
}
