import React from 'react';

const Login = () => {
    return (
    <div>
        <form action="https://ozxz43gxqh.execute-api.eu-west-2.amazonaws.com/api/join/full" id="example-form" method="post">
        <table>
            <tbody>
                <tr>
                    <td>Fist Name:</td>
                    <td><input name="name"/></td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td><input name="surname"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Subscribe"/></td>
                </tr>
            </tbody>
        </table>
        </form>
    </div>
    );
};

export default Login;