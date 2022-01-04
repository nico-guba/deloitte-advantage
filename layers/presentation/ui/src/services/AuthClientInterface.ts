export interface IAuthClient {
    login(username: string, password: string, onSuccess: Function, onError: Function): void,
}