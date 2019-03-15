using Microsoft.EntityFrameworkCore;
using System;
using System.Linq;

// Authors: Jake Walsh and Jordan Williams

namespace Holiday_API
{
    public class Holiday
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public string Date { get; set; }
    }

    public class HolidayContext : DbContext
    {
        public DbSet<Holiday> Holidays { get; set; }

        // localDB connection string
        // c:\users\gary\StudentDB1.mdf
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(@"Server=(localdb)\mssqllocaldb;Database=HolidaysDB;Trusted_Connection=True;");
        }
    }

    class Program
    {
        static void Main()
        {
            using (HolidayContext holidayContext = new HolidayContext())
            {
                // add holidays
                Holiday h1 = new Holiday() { Name = "Easter", Date = "Sunday, 21 April" };
                Holiday h2 = new Holiday() { Name = "Fathers Day", Date = "Sunday, 16 June" };

                holidayContext.Holidays.Add(h1);
                holidayContext.Holidays.Add(h2);
                holidayContext.SaveChanges();

                var holidaysQueryInOrder = holidayContext.Holidays.OrderBy(holiday => holiday.ID);
                foreach (var holiday in holidaysQueryInOrder)
                {
                    Console.WriteLine(holiday.ID + " " + holiday.Name + " " + holiday.Date);
                }
                Console.ReadLine();
            }
        }
    }
}
