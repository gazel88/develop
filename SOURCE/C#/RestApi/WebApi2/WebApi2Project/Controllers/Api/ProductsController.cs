using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebApi2Project.Models;

namespace WebApi2Project.Controllers.Api
{
    [RoutePrefix("api/products")]
    public class ProductsController : ApiController
    {
        static List<Product> products = new List<Product>()
        {
            new Product { Id = 1, Name = "Tomato Soup", Category = "Groceries", Price = 1 },
            new Product { Id = 2, Name = "Yo-yo", Category = "Toys", Price = 3.75M },
            new Product { Id = 3, Name = "Hammer", Category = "Hardware", Price = 16.99M }
        };

        [HttpGet]
        [Route("")]
        public IHttpActionResult GetAllProducts()
        {
            return Ok(products);
        }

        [HttpGet]
        [Route("{id:int}")]
        public IHttpActionResult GetProductById(int id)
        {
            var product = products.FirstOrDefault(p => p.Id == id);
            if (product == null)
                return NotFound();

            return Ok(product);
        }

        [HttpPost]
        [Route("")]
        public IHttpActionResult CreateProduct([FromBody] Product product)
        {
            if (product == null) return BadRequest("Invalid product");

            int newId = products.Any() ? products.Max(p => p.Id) + 1 : 1;
            product.Id = newId;
            products.Add(product);

            return Created($"api/products/{product.Id}", product);
        }

        [HttpPut]
        [Route("{id:int}")]
        public IHttpActionResult UpdateProduct(int id, [FromBody] Product updated)
        {
            var product = products.FirstOrDefault(p => p.Id == id);
            if (product == null)
                return NotFound();

            product.Name = updated.Name;
            product.Category = updated.Category;
            product.Price = updated.Price;

            return Ok(product);
        }

        [HttpDelete]
        [Route("{id:int}")]
        public IHttpActionResult DeleteProduct(int id)
        {
            var product = products.FirstOrDefault(p => p.Id == id);
            if (product == null)
                return NotFound();

            products.Remove(product);
            return Ok($"Product with ID {id} deleted");
        }
    }
}
