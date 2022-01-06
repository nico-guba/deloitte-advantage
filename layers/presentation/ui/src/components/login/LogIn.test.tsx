import { render, fireEvent, screen, Screen } from "@testing-library/react";
import React from "react";
import { IAuthClient } from "../../services/AuthClientInterface";
import LogIn from "./LogIn";

const authClient: IAuthClient = { login: () => { } };

describe("When Rendered", () => {

    it("should display username, password inputs and submit button", () => {
        render(<LogIn onSuccess={() => { }} authClient={authClient} />);

        expect(screen.getByTestId('username')).toHaveTextContent('EmailEmail');
        expect(screen.getByTestId('password')).toHaveTextContent('PasswordPassword');
        expect(screen.getByTestId('submit-login-button')).toHaveTextContent('Sign In');
    });
    it("should disable login button when Email and Passwords render as empty", () => {
        render(<LogIn onSuccess={() => { }} authClient={authClient} />);

        expect(screen.getByTestId('submit-login-button')).toBeDisabled();
    });
});

describe("Sign In Button", () => {
    it("should be disabled when Email field is empty", () => {
        render(<LogIn onSuccess={() => { }} authClient={authClient} />);
        inputPassword(screen, "PaS$W()RD");

        expect(screen.getByTestId('submit-login-button')).toBeDisabled();
    });
    it("should be disabled when Password field is empty", () => {
        render(<LogIn onSuccess={() => { }} authClient={authClient} />);
        inputEmail(screen, "me@email.com");

        expect(screen.getByTestId('submit-login-button')).toBeDisabled();
    });
    it("should be enabled when Email and Password inputted", () => {
        render(<LogIn onSuccess={() => { }} authClient={authClient} />);
        inputEmail(screen, "me@email.com");
        inputPassword(screen, "My_p@ssw0rD");

        expect(screen.getByTestId('submit-login-button')).not.toBeDisabled();
    });
    it("should call onError callback when login fails", async () => {
        const mockAuthClient: IAuthClient = { login: (a, b, c, error) => { error(); } };
        render(<LogIn onSuccess={() => { }} authClient={mockAuthClient} />);

        inputEmail(screen, "I_am@user.com");
        inputPassword(screen, "Ch@ng£_Me");
        clickSubmitSignIn(screen);

        expect(screen.getByText("Incorrect Username or Password.")).toHaveClass("Mui-error");
    });
    it("should call onSuccess callback when login is successful", async () => {
        const onSuccessMock = jest.fn();
        const mockAuthClient: IAuthClient = { login: (a, b, onSuccess, error) => { onSuccess(); } };
        render(<LogIn onSuccess={onSuccessMock} authClient={mockAuthClient} />);

        inputEmail(screen, "I_am@user.com");
        inputPassword(screen, "Ch@ng£_Me");
        clickSubmitSignIn(screen);

        expect(onSuccessMock).toHaveBeenCalledTimes(1);
    });
});

function inputEmail(component: Screen, value: string): void {
    fireEvent.change(component.getByLabelText("Email"), { target: { value: value } });
    expect(component.getByLabelText("Email")).toHaveValue(value);
}

function inputPassword(component: Screen, value: string): void {
    fireEvent.change(component.getByLabelText("Password"), { target: { value: value } });
    expect(component.getByLabelText("Password")).toHaveValue(value);
}

function clickSubmitSignIn(component: Screen) {
    fireEvent.click(component.getByTestId('submit-login-button'));
}
