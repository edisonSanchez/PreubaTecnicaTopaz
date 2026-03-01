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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel.DetailsProductViewModel

@Composable
fun DetailsProductView(
    productId: Int,
    viewModel: DetailsProductViewModel = hiltViewModel()
) {
    val detailsProduct by viewModel.detailsProduct.collectAsState()
    val resultAddFavoriteProduct by viewModel.resultAddFavoriteProduct.collectAsState()
    viewModel.getDetailsProduct(productId)

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
                text = "Precio: ${detailsProduct.price}$",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = "Rating: ${detailsProduct.rating}"
            )
            Button(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.addFavoriteProduct(detailsProduct)
                },
                enabled = resultAddFavoriteProduct <= 0
            ) {
                Text(text = "Agregar a favoritos")
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
            Toast.makeText(LocalContext.current, "Producto agregado a favoritos",
                Toast.LENGTH_SHORT).show()
        } else if (resultAddFavoriteProduct < 0) {
            Toast.makeText(LocalContext.current, "Ocurrio un error al agregar el" +
                    " producto a favoritos, intentalo mas tarde",
                Toast.LENGTH_SHORT).show()
        }
    }
}