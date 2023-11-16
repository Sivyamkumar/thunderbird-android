package app.k9mail.feature.account.setup.domain

import app.k9mail.autodiscovery.api.AutoDiscoveryResult
import app.k9mail.core.common.domain.usecase.validation.ValidationResult
import app.k9mail.feature.account.common.domain.entity.AccountOptions
import app.k9mail.feature.account.setup.AccountSetupExternalContract.AccountCreator.AccountCreatorResult
import com.fsck.k9.mail.FolderType
import com.fsck.k9.mail.ServerSettings
import com.fsck.k9.mail.folders.RemoteFolder

interface DomainContract {

    interface UseCase {
        fun interface GetAutoDiscovery {
            suspend fun execute(emailAddress: String): AutoDiscoveryResult
        }

        fun interface CreateAccount {
            suspend fun execute(
                emailAddress: String,
                incomingServerSettings: ServerSettings,
                outgoingServerSettings: ServerSettings,
                authorizationState: String?,
                options: AccountOptions,
            ): AccountCreatorResult
        }

        fun interface ValidateEmailAddress {
            fun execute(emailAddress: String): ValidationResult
        }

        fun interface ValidateConfigurationApproval {
            fun execute(isApproved: Boolean?, isAutoDiscoveryTrusted: Boolean?): ValidationResult
        }

        fun interface ValidateAccountName {
            fun execute(accountName: String): ValidationResult
        }

        fun interface ValidateDisplayName {
            fun execute(displayName: String): ValidationResult
        }

        fun interface ValidateEmailSignature {
            fun execute(emailSignature: String): ValidationResult
        }

        fun interface GetRemoteFolders {
            suspend fun execute(): List<RemoteFolder>
        }

        fun interface FilterRemoteFoldersForType {
            suspend fun execute(folderType: FolderType, folders: List<RemoteFolder>): List<RemoteFolder>
        }

        fun interface GetRemoteFoldersToFolderTypeMapping {
            fun execute(folders: List<RemoteFolder>): Map<FolderType, RemoteFolder?>
        }
    }
}
