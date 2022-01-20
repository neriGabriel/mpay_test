package com.example.mpaywalletapp.presentation.feature.card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.mpaywalletapp.R
import com.example.mpaywalletapp.domain.entity.Card
import com.example.mpaywalletapp.domain.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private val cardViewModel : CardViewModel by viewModel()
    private val args: CardFragmentArgs by navArgs()

    private var id: String? = null

    private lateinit var cardNumber: TextView
    private lateinit var cardName: TextView
    private lateinit var expirationDate: TextView
    private lateinit var totalAvailable: TextView
    private lateinit var totalLimit: TextView
    private lateinit var expirationLabel: TextView
    private lateinit var totalAvailableLabel: TextView
    private lateinit var totalLimitLabel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = args.cardId
        cardNumber = view.findViewById(R.id.card_number)
        cardName = view.findViewById(R.id.card_name)
        expirationDate = view.findViewById(R.id.expiration)
        totalAvailable = view.findViewById(R.id.total_available)
        totalLimit = view.findViewById(R.id.total_limit)
        expirationLabel = view.findViewById(R.id.expiration_label)
        totalAvailableLabel = view.findViewById(R.id.label_total_available)
        totalLimitLabel = view.findViewById(R.id.label_total_limit)

        cardViewModel.fetchCard(id)

        registerObserver()
    }

    private fun renderUI(card: Card) {
        cardNumber.text = card.cardNumber
        cardName.text = card.cardName
        expirationDate.text = card.expirationDate
        totalAvailable.text = card.availableLimit
        totalLimit.text = card.totalLimit
        expirationLabel.text = resources.getString(R.string.title_expiration_date)
        totalAvailableLabel.text = resources.getString(R.string.title_limit_available)
        totalLimitLabel.text = resources.getString(R.string.title_limit_total)
    }

    private fun registerObserver() {
        cardViewModel.card.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.let { card -> renderUI(card) }
                }

                Status.ERROR -> {
                    Toast.makeText(context, "Failed to load card info", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

}