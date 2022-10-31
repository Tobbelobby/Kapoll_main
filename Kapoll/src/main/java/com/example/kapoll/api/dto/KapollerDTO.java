package com.example.kapoll.api.dto;

import com.example.kapoll.Kapoll_db.tables.Kapoller;
import com.example.kapoll.Kapoll_db.tables.Poll;
import java.util.Set;
import java.util.stream.Collectors;

/** DTO for {@link Kapoller}. */
public class KapollerDTO {
  private final Long id;

  private final String firstName;
  private final String lastName;
  private final String userName;
  private final String password;

  private final Set<Long> pollIds;

  public KapollerDTO(
      Long id,
      String firstName,
      String lastName,
      String userName,
      String password,
      Set<Long> pollIds) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.pollIds = pollIds;
  }

  public static KapollerDTO map(Kapoller kapoller) {
    return new KapollerDTO(
        kapoller.getId(),
        kapoller.getFirstName(),
        kapoller.getLastName(),
        kapoller.getUserName(),
        kapoller.getPassword(),
        mapPolls(kapoller.getPolls()));
  }

  private static Set<Long> mapPolls(Set<Poll> polls) {
    return polls.stream().map(Poll::getId).collect(Collectors.toSet());
  }

  // Needed for Spring mapping to JSON.
  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public Set<Long> getPollIds() {
    return pollIds;
  }
}
