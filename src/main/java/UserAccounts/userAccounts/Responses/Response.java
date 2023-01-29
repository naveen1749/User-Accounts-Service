package UserAccounts.userAccounts.Responses;

import UserAccounts.userAccounts.Model.Status;

public  record Response<T>(Status status, T name){
}
