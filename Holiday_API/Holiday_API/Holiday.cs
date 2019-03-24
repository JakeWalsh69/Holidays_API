﻿using System;
using System.ComponentModel.DataAnnotations;

namespace Holiday_API
{
    public class Holiday
    {
        public int ID { get; set; }

        [Required]
        public string Name { get; set; }

        [Required]
        public string Date { get; set; }
    }
}
