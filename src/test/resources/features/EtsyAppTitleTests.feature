@etsyAppSection
Feature: Validating Etsy Titles

  Scenario Outline: Validating Etsy separate page title
    Given user navigates to Etsy application
    When user clicks on "<Section>" section
    Then user validates that title is "<Title>"

    Examples:
      | Section                  | Title                                                                    |
      | Jewelry and Accessories  | Jewelry & Accessories \| Etsy                                            |
      | End of Year Sales Event  | End of Year Sales Event \| Etsy                                          |
      | Clothing and shoes       | Clothing & Shoes \| Etsy                                                 |
      | Home and Living          | Home & Living \| Etsy                                                    |
      | Wedding and Party        | Wedding & Party \| Etsy                                                  |
      | Toys and Entertainment   | Toys & Entertainment \| Etsy                                             |
      | Art and Collectibles     | Art & Collectibles \| Etsy                                               |
      | Craft Supplies and Tools | Craft Supplies & Tools \| Etsy                                           |
      | Gifts and Gift Cards     | Etsy - Shop for handmade, vintage, custom, and unique gifts for everyone |
