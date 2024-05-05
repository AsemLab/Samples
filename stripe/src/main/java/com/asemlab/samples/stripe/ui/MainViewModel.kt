package com.asemlab.samples.stripe.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.stripe.models.performOnError
import com.asemlab.samples.stripe.models.performOnSuccess
import com.asemlab.samples.stripe.remote.StripeService
import com.asemlab.samples.stripe.utils.performRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val stripeService: StripeService) : ViewModel() {

    fun createEphemeralKey(
        customerId: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            performRequest { stripeService.createEphemeralKey(customerId) }.performOnSuccess {
                onSuccess(it.secret)
            }.performOnError {
                onError(it)
            }
        }
    }

    fun createPaymentIntent(
        customerId: String,
        amount: Int,
        currency: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            performRequest {
                stripeService.createPaymentIntent(
                    customerId,
                    amount,
                    currency
                )
            }.performOnSuccess {
                it.clientSecret?.let { secret ->
                    onSuccess(secret)
                }
            }.performOnError {
                onError(it)
            }
        }
    }

}