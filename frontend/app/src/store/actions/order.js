import * as actionTypes from './actionTypes';
import axios from '../../axios/axios-orders';

export const purchaseBurgerSuccess = (id, orderData) => {
    return {
        type: actionTypes.PURCHASE_BURGER_SUCCESS,
        orderId: id,
        orderData: orderData
    }
}

export const purchaseBurgerFail = (error) => {
    return {
        type: actionTypes.FETCH_INGREDIENTS_FAILED,
        error: error
    }
}

export const purchaseOrderStart = () => {
    return {
        type: actionTypes.PURCHASE_BURGER_START
    }
}

export const purchaseOrder = (orderData, tokenId) => {
    return dispatch => {
        dispatch(purchaseOrderStart());
        axios.post('/orders.json?auth=' + tokenId, orderData)
            .then(res => {
                dispatch(purchaseBurgerSuccess(res.data.name, orderData))
            })
            .catch(err => {
                dispatch(purchaseBurgerFail(err));
            });
    }
}

export const initPurchase = () => {
    return {
        type: actionTypes.PURCHASE_BURGER_INIT
    }
}

export const fetchOrdersSuccess = (orders) => {
    return {
        type: actionTypes.FETCH_ORDER_SUCCESS,
        orders: orders
    }
}

export const fetchOrdersFail = (error) => {
    return {
        type: actionTypes.FETCH_INGREDIENTS_FAILED,
        error: error
    }
}

export const fetchOrdersStart = () => {
    return {
        type: actionTypes.FETCH_ORDER_START
    }
}

export const fetchOrders = (tokenId, userId) => {
    return dispatch => {
        dispatch(fetchOrdersStart());

        const queryParams = `?auth=${tokenId}&orderBy="userId"&equalTo="${userId}"`;

        axios.get('orders.json' + queryParams)
            .then(res => {
                const fetchedOrder = [];

                for (let key in res.data) {
                    fetchedOrder.push({
                        ...res.data[key],
                        id: key
                    });
                }

                dispatch(fetchOrdersSuccess(fetchedOrder));
            })
            .catch(err => {
                dispatch(fetchOrdersFail(err));
            });
    }
}