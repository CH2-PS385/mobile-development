package com.ch2ps385.nutrimate.presentation.screen.auth.signin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.presentation.ui.theme.Shapes
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1
import com.ch2ps385.nutrimate.presentation.ui.theme.pSinopia
import com.ch2ps385.nutrimate.presentation.ui.theme.pSmashedPumpkin
import com.ch2ps385.nutrimate.presentation.ui.theme.solidWhite

@Composable
fun SignInScreen(
//    state : SignInState,
    onSignInClick : () -> Unit = {},
    modifier: Modifier = Modifier,
){
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passState = remember { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            color = pSmashedPumpkin,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = -250.dp)
            )
        }

        Surface(
            color = solidWhite,
            modifier = modifier
                .height(560.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 56.dp, topEnd = 56.dp),
        ){
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                Text(
                    text = "Welcome to NutriMate",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = pSinopia
                    ),
                    modifier = modifier
                        .padding(top = 28.dp)
                )
                Text(
                    text = "Sign in to your account",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = neutralColor1
                    ),
                    modifier = modifier
                        .padding(top = 8.dp, bottom = 22.dp)
                )
                OutlinedTextField(
                    value = emailState.value,
                    label = { Text(stringResource(id = R.string.email), style = MaterialTheme.typography.labelMedium)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = { newInput ->
                        emailState.value = newInput
                    },
                    shape = Shapes.medium,
                    modifier = modifier
                        .width(312.dp)

                )
                OutlinedTextField(
                    value = passState.value,
                    label = { Text(stringResource(id = R.string.password), style = MaterialTheme.typography.labelMedium)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { newInput ->
                       passState.value = newInput
                    },
                    shape = Shapes.medium,
                    modifier = modifier
                        .width(312.dp)
                )
                Button(
                    onClick = {},
                    shape = Shapes.small,
                    colors = ButtonDefaults.buttonColors(containerColor = pSinopia),
                    modifier = modifier
                        .shadow(
                            elevation =10.dp,
                            spotColor = Color(0x4DC73A07),
                            ambientColor = Color(0x4DC73A07)
                        )
                        .padding(top = 24.dp)
                        .width(126.dp)
                        .height(40.dp),
                    ) {
                    Text(text = "Log In")
                }
                Text(
                    text = "Forgot password?",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = neutralColor1
                    ),
                    modifier = modifier
                        .padding(top =16.dp)
                )
                Text(
                    text = "Don’t have an account?  Sign up",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = neutralColor1
                    ),
                    modifier = modifier
                        .padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Divider(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .padding(end = 8.dp),  // Jarak antara garis dan teks
                        color = neutralColor1
                    )

                    Text(
                        text = "Or",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = neutralColor1
                    )

                    Divider(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .padding(start = 8.dp),
                        color = neutralColor1
                    )
                }
                Button(
                    onClick = onSignInClick,
                    border = BorderStroke(1.dp, color = neutralColor1),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = neutralColor1),
                    modifier = modifier
                        .padding(bottom = 16.dp)
                        .width(312.dp)
                        .height(40.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_google),
                        contentDescription ="Sign up with Google",
                        modifier = Modifier.size(20.dp))

                    Text(
                        text = "Sign up with Google",
                        Modifier.padding(start = 10.dp),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
                Button(
                    onClick =  onSignInClick,
                    border = BorderStroke(1.dp, color = neutralColor1),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = neutralColor1),
                    modifier = modifier
                        .width(312.dp)
                        .height(40.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_google),
                        contentDescription ="Sign IN button",
                        modifier = Modifier.size(20.dp))
                    Text(
                        text = "Sign In with Google",
                        Modifier.padding(start = 10.dp),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.PIXEL_4
)
fun LoginScreenPreview(){
    NutriMateTheme {
        SignInScreen()
    }
}


@Composable
fun ButtonWithBorder(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        border = BorderStroke(1.dp, color = neutralColor1),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = neutralColor1),
        modifier = modifier
            .width(312.dp)
            .height(40.dp)
    ) {
        Image(
            painterResource(id = R.drawable.ic_email),
            contentDescription ="Cart button icon",
            modifier = Modifier.size(20.dp))

        Text(text = "Add to cart",Modifier.padding(start = 10.dp))
    }
}


@Preview(
    showBackground = true,
    device = Devices.PIXEL_4
)
@Composable
fun ButtonWithBorderPreview(){
    NutriMateTheme {
        ButtonWithBorder()
    }
}

