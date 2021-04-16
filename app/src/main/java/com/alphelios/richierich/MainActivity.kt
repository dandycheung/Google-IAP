package com.alphelios.richierich

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphelios.iap.IapConnector
import com.alphelios.iap.PurchaseServiceListener
import com.alphelios.iap.SubscriptionServiceListener
import com.alphelios.richierich.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomnavview.itemIconTintList = null

        val skuList = listOf("base", "moderate", "quite", "plenty", "yearly")
        val subsList = listOf("subscription")

        val iapConnector = IapConnector(
            this,
            skuList,
            subsList,
            "LICENSE KEY",
            true
        )

        iapConnector.addPurchaseListener(object : PurchaseServiceListener {
            override fun onPricesUpdated(iapKeyPrices: Map<String, String>) {
                // list of available products will be received here, so you can update UI with prices if needed
            }

            override fun onProductPurchased(sku: String?) {
                when (sku) {
                    "base" -> {

                    }
                    "moderate" -> {

                    }
                    "quite" -> {

                    }
                    "plenty" -> {

                    }
                    "yearly" -> {

                    }
                }
            }

            override fun onProductRestored(sku: String?) {
                // will be triggered fetching owned products using IAPManager.init();
            }
        })

        iapConnector.addSubscriptionListener(object : SubscriptionServiceListener {
            override fun onSubscriptionRestored(sku: String?) {
                // will be triggered upon fetching owned subscription upon initialization
            }

            override fun onSubscriptionPurchased(sku: String?) {
                // will be triggered whenever subscription succeeded
                when(sku){
                    "subscription"->{

                    }
                }
            }

            override fun onPricesUpdated(iapKeyPrices: Map<String, String>) {
                // list of available products will be received here, so you can update UI with prices if needed
            }
        })

        binding.btPurchaseCons.setOnClickListener {
            iapConnector.purchase(this, "base")
        }
        binding.btnMonthly.setOnClickListener {
            iapConnector.subscribe(this, "subscription")
        }

        binding.btnYearly.setOnClickListener {
            iapConnector.purchase(this, "yearly")
        }
        binding.btnQuite.setOnClickListener {
            iapConnector.purchase(this, "quite")

        }
        binding.btnModerate.setOnClickListener {
            iapConnector.purchase(this, "moderate")
        }

        binding.btnUltimate.setOnClickListener {
            iapConnector.purchase(this, "plenty")

        }
    }
}