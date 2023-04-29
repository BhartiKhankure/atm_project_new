import { Routes } from "@angular/router";
import { AtmEventsListComponent } from "./atmEvents/atmEvents-list.component";
import { DepositComponent } from "./atmEvents/deposit.component";
import { TransactionHistoryComponent } from "./atmEvents/transactionHistory.component";
import { TransferAmountComponent } from "./atmEvents/transferAmount.component";
import { WithdrawComponent } from "./atmEvents/withdraw.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";

export const appRoutes: Routes = [
    { path: 'login', component: LoginComponent, },
    { path: 'register', component: RegisterComponent },
    { path: 'atmEvents', component: AtmEventsListComponent },
    { path: 'transaction-history', component: TransactionHistoryComponent },
    { path: 'transfer-amount', component: TransferAmountComponent },
    { path: 'withdraw', component: WithdrawComponent },
    { path: 'deposit', component: DepositComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'createUser', component: RegisterComponent}
]