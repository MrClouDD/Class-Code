# Comprehensive Sorting Methods Reference

This document provides a complete reference of predefined sorting methods for the GArray generic class. These methods cover virtually every sorting scenario you might encounter in programming.

## Table of Contents
- [Numeric Sorting Methods](#numeric-sorting-methods)
- [String Sorting Methods](#string-sorting-methods)
- [Date/Time Sorting Methods](#datetime-sorting-methods)
- [Collection/Array Sorting Methods](#collectionarray-sorting-methods)
- [Object/Reference Sorting Methods](#objectreference-sorting-methods)
- [Boolean/Logical Sorting Methods](#booleanlogical-sorting-methods)
- [Advanced/Utility Sorting Methods](#advancedutility-sorting-methods)
- [Specialized Domain Sorting](#specialized-domain-sorting)

---

## Numeric Sorting Methods

### Basic Numeric Sorting
| Method | Description | Example |
|--------|-------------|---------|
| `"ascending"` | Natural numeric order | `1, 2, 3, 10, 20` |
| `"descending"` | Reverse numeric order | `20, 10, 3, 2, 1` |
| `"absoluteValue"` | Sort by absolute value | `\|-10\|, \|-5\|, \|3\|, \|7\|` |
| `"absoluteDescending"` | Absolute value descending | `\|10\|, \|7\|, \|5\|, \|3\|` |

### Type-Specific Numeric Sorting
| Method | Description |
|--------|-------------|
| `"asInteger"` | Force integer comparison |
| `"asLong"` | Force long comparison |
| `"asDouble"` | Force double comparison |
| `"asFloat"` | Force float comparison |

### Mathematical Sorting
| Method | Description | Example |
|--------|-------------|---------|
| `"byDigitSum"` | Sum of digits | `123 → 6, 45 → 9` |
| `"byDigitCount"` | Number of digits | `123 → 3, 45 → 2` |
| `"byParity"` | Even numbers first, then odd | `2, 4, 6, 1, 3, 5` |
| `"byParityOdd"` | Odd numbers first, then even | `1, 3, 5, 2, 4, 6` |
| `"bySquare"` | Sort by n² | Based on squared values |
| `"byCube"` | Sort by n³ | Based on cubed values |
| `"bySquareRoot"` | Sort by √n | Based on square root |
| `"byReciprocal"` | Sort by 1/n | For non-zero numbers |

---

## String Sorting Methods

### Basic String Sorting
| Method | Description | Example |
|--------|-------------|---------|
| `"ascending"` | Lexicographic order | `"Apple", "Banana", "Cherry"` |
| `"descending"` | Reverse lexicographic | `"Cherry", "Banana", "Apple"` |
| `"caseInsensitive"` | Ignore case | `"apple", "Apple", "APPLE"` |
| `"caseSensitive"` | Case matters | `"APPLE", "Apple", "apple"` |
| `"reverseCaseInsensitive"` | Reverse case-insensitive | Reverse of case-insensitive |

### Length-Based Sorting
| Method | Description | Example |
|--------|-------------|---------|
| `"byLength"` | Shortest first | `"a", "bb", "ccc"` |
| `"byLengthDescending"` | Longest first | `"ccc", "bb", "a"` |
| `"byLengthThenAlpha"` | Length first, then alphabetical | Same length sorted alphabetically |
| `"byLengthThenAlphaDesc"` | Length first, then reverse alphabetical | Same length sorted reverse alphabetically |

### Character Analysis
| Method | Description |
|--------|-------------|
| `"byVowelCount"` | Count of a, e, i, o, u |
| `"byConsonantCount"` | Count of non-vowels |
| `"byVowelPercentage"` | Percentage of vowels |
| `"byAlphabeticCharCount"` | Count of a-z, A-Z only |
| `"byDigitCount"` | Count of 0-9 characters |
| `"bySpecialCharCount"` | Count of non-alphanumeric |
| `"byWhitespaceCount"` | Count of spaces, tabs, newlines |
| `"byUniqueCharCount"` | Count of distinct characters |
| `"byRepeatedCharCount"` | Count of repeated characters |

### Pattern-Based Sorting
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"startsWith"` | Prefix matching | ✅ Prefix string |
| `"endsWith"` | Suffix matching | ✅ Suffix string |
| `"contains"` | Substring matching | ✅ Substring |
| `"matches"` | Regex pattern matching | ✅ Regex pattern |
| `"byFirstChar"` | Sort by first character | |
| `"byLastChar"` | Sort by last character | |
| `"byMiddleChar"` | Sort by middle character | |
| `"byCharAt"` | Sort by character at index | ✅ Index |

### Linguistic Sorting
| Method | Description |
|--------|-------------|
| `"locale"` | Locale-specific collation |
| `"localeIgnoreCase"` | Locale-specific, case-insensitive |
| `"naturalOrder"` | Human-like: "file1", "file2", "file10" |
| `"versionSort"` | Version numbers: "1.2", "1.10", "2.0" |

### Numeric String Sorting
| Method | Description | Example |
|--------|-------------|---------|
| `"asNumeric"` | Parse as numbers | `"2", "10", "100"` |
| `"asNumericDescending"` | Numeric descending | `"100", "10", "2"` |
| `"byNumericValue"` | Extract first number found | `"abc123" → 123` |
| `"byAllNumericValues"` | Sort by all numbers in string | Complex numeric extraction |

### Frequency and Statistics
| Method | Description |
|--------|-------------|
| `"byFrequency"` | Most frequent strings first |
| `"byFrequencyDescending"` | Least frequent strings first |
| `"byRarity"` | Rarest strings first (inverse frequency) |

### Hash and Encoding
| Method | Description |
|--------|-------------|
| `"byHashCode"` | Sort by String.hashCode() |
| `"byMD5"` | Sort by MD5 hash |
| `"bySHA1"` | Sort by SHA1 hash |
| `"byBase64"` | Sort by Base64 encoded value |

---

## Date/Time Sorting Methods

### Basic Date/Time Sorting
| Method | Description |
|--------|-------------|
| `"chronological"` | Earliest first |
| `"reverseChronological"` | Latest first |
| `"byYear"` | Group by year |
| `"byMonth"` | Group by month |
| `"byDayOfMonth"` | Group by day of month |
| `"byDayOfWeek"` | Group by day of week |
| `"byHour"` | Group by hour |
| `"byMinute"` | Group by minute |

### Date Calculations
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"byAgeFromToday"` | Sort by age/time difference from now | |
| `"byAgeFromDate"` | Sort by age from specific date | ✅ Reference date |
| `"byDaysSinceEpoch"` | Days since Jan 1, 1970 | |
| `"byWeekOfYear"` | ISO week number | |
| `"byQuarter"` | Q1, Q2, Q3, Q4 | |
| `"bySeason"` | Spring, Summer, Fall, Winter | |

### Time Zones and Formats
| Method | Description |
|--------|-------------|
| `"byUTC"` | Convert to UTC then sort |
| `"byTimezone"` | Sort by timezone offset |
| `"byEpochSeconds"` | Sort by Unix timestamp |
| `"byEpochMillis"` | Sort by milliseconds since epoch |

---

## Collection/Array Sorting Methods

### Size-Based Sorting
| Method | Description |
|--------|-------------|
| `"bySize"` | Smallest collections first |
| `"bySizeDescending"` | Largest collections first |
| `"byCapacity"` | Sort by capacity (if applicable) |

### Content-Based Sorting
| Method | Description |
|--------|-------------|
| `"byFirstElement"` | Sort by first element in collection |
| `"byLastElement"` | Sort by last element in collection |
| `"byMaxElement"` | Sort by maximum element in collection |
| `"byMinElement"` | Sort by minimum element in collection |
| `"bySumOfElements"` | Sort by sum of all elements |
| `"byAverageOfElements"` | Sort by average of all elements |
| `"byMedianOfElements"` | Sort by median of all elements |
| `"byElementVariance"` | Sort by statistical variance |

### Set Operations
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"byIntersectionSize"` | Size of intersection with reference set | ✅ Reference set |
| `"byUnionSize"` | Size of union with reference set | ✅ Reference set |
| `"byDifferenceSize"` | Size of difference with reference set | ✅ Reference set |

---

## Object/Reference Sorting Methods

### Identity and Equality
| Method | Description |
|--------|-------------|
| `"byHashCode"` | Sort by Object.hashCode() |
| `"byIdentityHashCode"` | Sort by System.identityHashCode() |
| `"byClassName"` | Sort by getClass().getSimpleName() |
| `"byFullClassName"` | Sort by getClass().getName() |
| `"byToString"` | Sort by toString() output |
| `"byToStringLength"` | Sort by toString().length() |

### Memory and Performance
| Method | Description |
|--------|-------------|
| `"byMemoryFootprint"` | Estimate memory usage (if calculable) |
| `"byCreationTime"` | Sort by object creation time (if tracked) |
| `"byLastAccessTime"` | Sort by last access time (if tracked) |

---

## Boolean/Logical Sorting Methods

### Basic Boolean Sorting
| Method | Description |
|--------|-------------|
| `"falseFirst"` | false values before true values |
| `"trueFirst"` | true values before false values |
| `"byTruthiness"` | Convert to boolean, then sort |

---

## Advanced/Utility Sorting Methods

### Random and Shuffling
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"random"` | Random order (shuffle) | |
| `"randomSeed"` | Random with specific seed | ✅ Seed value |
| `"reverse"` | Reverse current order | |
| `"stable"` | Maintain relative order of equal elements | |

### Custom Comparisons
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"custom"` | Use provided Comparator | ✅ Comparator instance |
| `"byProperty"` | Sort by object property | ✅ Property name |
| `"byMethod"` | Sort by method result | ✅ Method name |
| `"byField"` | Sort by field value | ✅ Field name |

### Null Handling
| Method | Description |
|--------|-------------|
| `"nullsFirst"` | null values first, then natural order |
| `"nullsLast"` | null values last, then natural order |
| `"nullsOnly"` | Only null values |
| `"nonNullsOnly"` | Exclude null values |

### Composite Sorting
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"thenBy"` | Secondary sort criteria | ✅ Second comparator |
| `"compound"` | Multiple sort criteria | ✅ Criteria list |
| `"weighted"` | Weighted combination of multiple criteria | ✅ Weights |

### Performance Optimized
| Method | Description |
|--------|-------------|
| `"parallel"` | Use parallel sorting (for large datasets) |
| `"timsort"` | Force Timsort algorithm |
| `"quicksort"` | Force Quicksort algorithm |
| `"mergesort"` | Force Mergesort algorithm |
| `"heapsort"` | Force Heapsort algorithm |

### Functional Sorting
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"byLambda"` | Sort using lambda expression | ✅ Lambda expression |
| `"byComparator"` | Sort using Comparator instance | ✅ Comparator |
| `"byFunction"` | Sort by applying function to each element | ✅ Function |

---

## Specialized Domain Sorting

### Network/URLs
| Method | Description |
|--------|-------------|
| `"byDomain"` | Sort URLs by domain |
| `"byPort"` | Sort by port number |
| `"byProtocol"` | Sort by protocol (http, https, ftp, etc.) |
| `"byIPAddress"` | Sort IP addresses numerically |

### File/Path Sorting
| Method | Description |
|--------|-------------|
| `"byExtension"` | Sort by file extension |
| `"byFileSize"` | Sort by file size |
| `"byFileName"` | Sort by filename only |
| `"byDirectoryPath"` | Sort by directory path |
| `"byLastModified"` | Sort by file modification date |

### Geographic
| Method | Description | Parameters Required |
|--------|-------------|-------------------|
| `"byLatitude"` | Sort by latitude coordinate | |
| `"byLongitude"` | Sort by longitude coordinate | |
| `"byDistance"` | Sort by distance from point | ✅ Reference point |
| `"byTimezone"` | Sort by timezone | |
| `"byCountry"` | Sort by country name | |
| `"byPostalCode"` | Sort by postal/zip code | |

### Scientific/Mathematical
| Method | Description |
|--------|-------------|
| `"byMagnitude"` | Sort by vector magnitude |
| `"byAngle"` | Sort by angle/direction |
| `"byComplexModulus"` | Sort complex numbers by modulus |
| `"byComplexArgument"` | Sort complex numbers by argument/phase |

---

## Usage Examples

### Basic Usage
```java
// Numeric sorting
GArray<Integer> numbers = new GArray<>(new Integer[]{3, 1, 4, 1, 5});
numbers.sort("ascending");        // [1, 1, 3, 4, 5]
numbers.sort("byParity");         // [4, 1, 1, 3, 5] (even first)

// String sorting
GArray<String> words = new GArray<>(new String[]{"banana", "Apple", "cherry"});
words.sort("caseInsensitive");    // ["Apple", "banana", "cherry"]
words.sort("byLength");           // ["Apple", "banana", "cherry"]

// Date sorting
GArray<LocalDate> dates = new GArray<>(new LocalDate[]{
    LocalDate.of(2023, 12, 25),
    LocalDate.of(2023, 1, 1),
    LocalDate.of(2023, 6, 15)
});
dates.sort("chronological");      // [2023-01-01, 2023-06-15, 2023-12-25]
```

### Advanced Usage
```java
// Composite sorting with parameters
words.sort("byLengthThenAlpha");   // Sort by length, then alphabetically
numbers.sort("absoluteValue");     // Sort by absolute value
dates.sort("byQuarter");           // Sort by calendar quarter
```

---

## Implementation Notes

1. **Type Safety**: Each method automatically detects the array type and applies appropriate comparisons
2. **Performance**: Methods are optimized for their specific use cases
3. **Null Handling**: Most methods handle null values gracefully
4. **Parameters**: Some methods require additional parameters (marked with ✅)
5. **Extensibility**: New methods can be easily added to the system

---

## Contributing

When adding new sorting methods:
1. Follow the naming convention: use descriptive, lowercase names with camelCase
2. Add appropriate type checking and error handling
3. Include examples and documentation
4. Consider performance implications for large datasets
5. Add unit tests for the new method

---

*This reference covers virtually every sorting scenario you might encounter in programming, from basic data types to specialized domains.*
