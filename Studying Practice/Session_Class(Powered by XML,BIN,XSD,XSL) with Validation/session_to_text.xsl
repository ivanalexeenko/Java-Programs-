<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:functions="com.session.CustomSAXParser">
    <xsl:output method="text"/>

    <xsl:template match="/">
        Session Results:
        <xsl:for-each select="session/sessionData">
           [ Number = "<xsl:value-of select="//number/@name"/>" || Surname = "<xsl:value-of select="//surname/@name"/>" || Subject = "<xsl:value-of select="//subject/@name"/>" || Mark = "<xsl:value-of select="//mark/@name"/>" ]
        </xsl:for-each>
            Popular Subject is "<xsl:value-of select="functions:getPopular()"/>" and Average Mark equals "<xsl:value-of select="functions:getAverage()"/>"
    </xsl:template>
</xsl:stylesheet>