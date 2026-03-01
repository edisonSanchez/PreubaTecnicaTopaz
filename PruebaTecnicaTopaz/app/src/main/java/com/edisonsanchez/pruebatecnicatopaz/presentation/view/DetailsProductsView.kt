package com.edisonsanchez.pruebatecnicatopaz.presentation.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.edisonsanchez.pruebatecnicatopaz.R
import com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel.DetailsProductViewModel

@Composable
fun DetailsProductView(
    productId: Int,
    viewModel: DetailsProductViewModel = hiltViewModel()
) {
    val detailsProduct by viewModel.detailsProduct.collectAsState()
    val resultAddFavoriteProduct by viewModel.resultAddFavoriteProduct.collectAsState()
    val resultRemoveFavoriteProduct by viewModel.resultRemoveFavoriteProduct.collectAsState()
    val resultCheckIsFavoriteProduct by viewModel.resultCheckIsFavoriteProduct.collectAsState()
    viewModel.getDetailsProduct(productId)
    viewModel.checkIsFavoriteProduct(productId)
    Column(
        modifier = Modifier
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        detailsProduct?.let { detailsProduct ->
            Text(
                fontSize = 20.sp,
                text = detailsProduct.title,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            AsyncImage(
                model = detailsProduct.images[0],
                contentDescription = null
            )
            Text(
                fontSize = 16.sp,
                color = Color.Gray,
                text = detailsProduct.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = stringResource(R.string.text_price, detailsProduct.price),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = stringResource(R.string.text_rating, detailsProduct.rating)
            )
            Button(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.addFavoriteProduct(detailsProduct)
                },
                enabled = resultCheckIsFavoriteProduct.not()
            ) {
                Text(text = stringResource(R.string.text_button_add))
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.removeFavoriteProduct(detailsProduct.id)
                },
                enabled = resultCheckIsFavoriteProduct
            ) {
                Text(text = stringResource(R.string.text_button_delete))
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (resultAddFavoriteProduct > 0){
            Toast.makeText(LocalContext.current,
                stringResource(R.string.message_add_product),
                Toast.LENGTH_SHORT).show()
        } else if (resultAddFavoriteProduct < 0) {
            Toast.makeText(LocalContext.current,
                stringResource(R.string.message_error_add_product),
                Toast.LENGTH_SHORT).show()
        }
        if (resultRemoveFavoriteProduct > 0){
            Toast.makeText(LocalContext.current,
                stringResource(R.string.message_remove_product),
                Toast.LENGTH_SHORT).show()
        } else if (resultRemoveFavoriteProduct == 0) {
            Toast.makeText(LocalContext.current,
                stringResource(R.string.message_error_remove_product),
                Toast.LENGTH_SHORT).show()
        }
    }
}