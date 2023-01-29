package UserAccounts.userAccounts.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.common.value.qual.ArrayLen;

@Getter
@AllArgsConstructor
public enum Status {

    UPDATED("USER_UPDATED"),
    DELETED("USER_DELETED"),
    CREATED("USER_CREATED"),
    UPDATE_FAILURE("USER_UPDATE_FAILED"),
    CREATE_FAILURE("USER_CREATION_FAILURE"),
    DELETE_FAILURE("USER_DELETION_FAIlURE"),
    NOTFOUND("USER_NOT_FOUND"),
    INVALID_DETAILS("USER_DETAILS_NOT_VALID"),
    DB_CONNECTION_CLOSED("DB_CONNECTION_CLOSED"),
    DB_CONNECTiON_FAILURE("DB_CONNECTION_FAILURE"),
    OK("ACCEPTED");
    private final String messsage;

}
