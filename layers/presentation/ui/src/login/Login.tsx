import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import React, { useState } from "react";
import Container from "@mui/material/Container";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import { IAuthClient } from "../services/AuthClientInterface";
import AdvantageLogo from "../components/AdvantageLogo";

interface IProps {
    authClient: IAuthClient;
    onSuccess(): void;
}

export default function LogIn(props: IProps) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loginResultMessage, setLoginResultMessage] = useState("");

    function disableSignInButton(): boolean {
        return !username || !password;
    }

    function logIn() {
        const onSuccess = () => {
            props.onSuccess();
        };
        const onError = () => {
            // TODO: show error message returned from Login Client
            setLoginResultMessage("Incorrect Username or Password.");
        };
        props.authClient.login(username, password, onSuccess, onError);
    }

    return (
        <Container maxWidth="xs">
            <div style={{
                marginTop: "20px",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
            }} >
                <Card variant="outlined">
                    <CardContent>
                        <AdvantageLogo />
                    </CardContent>
                    <CardContent>
                        <Typography gutterBottom variant="h6" component="div">Sign In</Typography>
                        <TextField
                            data-testid="username"
                            label="Email"
                            value={username}
                            fullWidth
                            variant="outlined"
                            margin="normal"
                            autoFocus
                            onChange={(event) => setUsername(event.target.value)}
                            // TODO: Enable Enter key ?
                            // onKeyPress={(event) => {
                            //     if (event.key === "Enter") {
                            //         logIn();
                            //         event.preventDefault();
                            //     }
                            // }}
                            error={loginResultMessage.length > 0}
                        />
                        <TextField
                            data-testid="password"
                            label="Password"
                            type="password"
                            value={password}
                            fullWidth
                            variant="outlined"
                            margin="normal"
                            onChange={(event) => setPassword(event.target.value)}
                            // onKeyPress={(event) => {
                            //     if (event.key === "Enter") {
                            //         logIn();
                            //         event.preventDefault();
                            //     }
                            // }}
                            error={loginResultMessage.length > 0}
                            helperText={loginResultMessage}
                        />
                        <CardActions>
                            <Button
                                data-testid="submit-login-button"
                                color="primary"
                                variant="contained"
                                disabled={disableSignInButton()}
                                onClick={() => logIn()}
                            >
                                Sign In
                            </Button>
                        </CardActions>
                    </CardContent>
                </Card >
            </div >
        </Container >
    );
}
