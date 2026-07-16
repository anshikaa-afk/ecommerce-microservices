import { Routes } from '@angular/router';
import { ProductsComponent } from './components/products/products';
import { LoginComponent } from './components/login/login';
import { AiChatComponent } from './components/ai-chat/ai-chat';
import { OrdersComponent } from './components/orders/orders';
import { CartComponent } from './components/cart/cart';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'products',
        pathMatch: 'full'
    },
    {
        path: 'products',
        component: ProductsComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'cart',
        component: CartComponent
    },
    {
        path: 'orders',
        component: OrdersComponent
    },
    {
        path: 'ai',
        component: AiChatComponent
    }
];
