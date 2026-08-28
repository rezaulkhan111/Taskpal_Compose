package machine.code.taskpal.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import machine.code.taskpal.presentation.ui.components.TaskpalBottomNavigation
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme
import machine.code.taskpal.presentation.viewmodel.ProfileVM

@Composable
fun AccountScreen(
    onNavigate: (String) -> Unit,
    onBack: () -> Unit = {},
    viewModel: ProfileVM = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = { TaskpalBottomNavigation(currentScreen = "account", onScreenSelected = onNavigate) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            ProfileHeader(
                userName = state.userName,
                profileImage = state.profileImage
            )

            Spacer(modifier = Modifier.height(32.dp))

            SectionHeader(title = "Preferences")
            Spacer(modifier = Modifier.height(16.dp))
            
            state.preferences.forEach { item ->
                PreferenceToggleItem(
                    icon = item.icon,
                    title = item.title,
                    checked = item.isEnabled,
                    onCheckedChange = { viewModel.togglePreference(item.id, it) }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            SectionHeader(title = "Support & Info")
            Spacer(modifier = Modifier.height(16.dp))
            
            state.supportInfo.forEach { item ->
                SupportItem(
                    icon = item.icon,
                    title = item.title,
                    onClick = { /* TODO: Handle click */ }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Surface(
                onClick = { viewModel.logout() },
                color = Color.Transparent,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Logout,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Log Out",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ProfileHeader(userName: String, profileImage: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profileImage,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = userName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.height(32.dp)
        ) {
            Text(text = "Edit Profile", fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun PreferenceToggleItem(
    icon: ImageVector,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SupportItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Gray
    )
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    TaskpalTheme {
        AccountScreen(onNavigate = {})
    }
}
