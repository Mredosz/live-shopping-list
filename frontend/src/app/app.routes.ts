import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Lists } from './shopping-list/lists/lists';
import { Layout } from './layout/layout/layout';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'register',
    component: Register,
  },
  {
    path: 'app',
    component: Layout,
    children: [
      {
        path: 'lists',
        component: Lists,
      },
    ],
  },
];
