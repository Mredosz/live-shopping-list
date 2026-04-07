import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './auth/register/register';
import { Lists } from './shopping-list/lists/lists';
import { Layout } from './layout/layout/layout';
import { ListDetails } from './shopping-list/lists/list/list-details/list-details';

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
      {
        path: 'lists/:id',
        component: ListDetails,
      },
    ],
  },
];
