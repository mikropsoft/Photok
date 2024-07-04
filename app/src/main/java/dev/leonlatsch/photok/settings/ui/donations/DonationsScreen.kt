/*
 *   Copyright 2020-2024 Leon Latsch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package dev.leonlatsch.photok.settings.ui.donations

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.leonlatsch.photok.R
import dev.leonlatsch.photok.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationsScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Donation Methods") },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxHeight(),
            ) {
                DonationMethodItem(title = "PayPal", description = "paypal.me/leonlatschdev") {
                    OpenInBrowserButton(url = "https://paypal.me/leonlatsch")
                }

                Spacer(modifier = Modifier.height(24.dp))

                DonationMethodItem(title = "Ko-Fi", description = "ko-fi.com/leonlatsch") {
                    OpenInBrowserButton(url = "https://ko-fi.com/leonlatsch")
                }

                Spacer(modifier = Modifier.height(24.dp))

                DonationMethodItem(title = "Liberapay", description = "liberapay.org/leonlatsch") {
                    OpenInBrowserButton(url = "https://liberapay.org/leonlatsch")
                }

                Spacer(modifier = Modifier.height(24.dp))

                DonationMethodItem(title = "Bitcoin", description = "bc1qd4enyt30w02vty8xt3fm5uesunzxpnz3w4agf4") {
                    CopyButton(valueToCopy = "bc1qd4enyt30w02vty8xt3fm5uesunzxpnz3w4agf4")
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "By donating to this project ...",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    )
}

@Composable
private fun OpenInBrowserButton(modifier: Modifier = Modifier, url: String) {
    val uriHandler = LocalUriHandler.current
    IconButton(
        onClick = { uriHandler.openUri(url) }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_forward),
            contentDescription = "Open in Browser"
        )
    }

}

@Composable
private fun CopyButton(modifier: Modifier = Modifier, valueToCopy: String) {
    val clipboardManager = LocalClipboardManager.current
    IconButton(
        onClick = { clipboardManager.setText(AnnotatedString(valueToCopy)) }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_copy),
            contentDescription = "Copy"
        )
    }
}

@Composable
private fun DonationMethodItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    action: @Composable () -> Unit
) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // TODO: Add a nice icon
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    ),
            ) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            action()
        }
    }

}

@Preview()
@Composable
private fun DonationsScreenPreviewLight() {
    AppTheme {
        DonationsScreen(
            onClose = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DonationsScreenPreviewDark() {
    AppTheme {
        DonationsScreen(
            onClose = {}
        )
    }
}
