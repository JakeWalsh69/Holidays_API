using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Holiday_API;

namespace Holiday_API.Models
{
    public class Holiday_APIContext : DbContext
    {
        public Holiday_APIContext (DbContextOptions<Holiday_APIContext> options)
            : base(options)
        {
        }

        public DbSet<Holiday_API.Holiday> Holiday { get; set; }
    }
}
