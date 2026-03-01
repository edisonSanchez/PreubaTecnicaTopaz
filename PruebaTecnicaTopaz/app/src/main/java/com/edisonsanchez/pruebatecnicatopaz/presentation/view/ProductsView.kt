package com.edisonsanchez.pruebatecnicatopaz.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.edisonsanchez.pruebatecnicatopaz.R
import com.edisonsanchez.pruebatecnicatopaz.data.Product
import com.edisonsanchez.pruebatecnicatopaz.presentation.view.theme.PruebaTecnicaTopazTheme
import com.edisonsanchez.pruebatecnicatopaz.presentation.viewModel.ProductsViewModel

@Composable
fun ProductsView(
    viewModel: ProductsViewModel = hiltViewModel(),
    navController: NavController
) {
    val products by viewModel.products.collectAsState()
    products?.let { products ->
        if (products.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.message_not_found_products))
            }
        } else {
            LazyColumn {
                items(products) { product ->
                    ProductItem(product = product) { id ->
                        navController.navigate("${Details.route}/$id")
                    }
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: (Int) -> Unit) {
    Card(
        onClick = { onClick(product.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = product.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${product.price} $",
                    color = Color.Gray,
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsViewPreview() {
    PruebaTecnicaTopazTheme {
        ProductsView(navController = NavController(LocalContext.current))
    }
}