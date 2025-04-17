/***
 * Excerpted from "Test Drive ASP.NET MVC",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/jmasp for more book information.
***/
[SetUp]
public void setup()
{
  NHibernateConfiguration.Init(
    MsSqlConfiguration.MsSql2005.ConnectionString(
    builder =>
    builder.Server("JM6333\\SQLEXPRESS").
    Database("test_GetOrganized").
    TrustedConnection()),
    RebuildDatabase);
    session = NHibernateConfiguration.CreateAndOpenSession();
}

private void RebuildDatabase(Configuration config)
{
  new SchemaExport(config).Create(false, true);
  config.SetInterceptor(new LocalMachineProdSimulatorInterceptor());
}